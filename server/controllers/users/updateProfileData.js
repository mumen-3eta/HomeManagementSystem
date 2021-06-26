const boom = require('@hapi/boom');
const { hash } = require('bcrypt');

const { profileDataSchema } = require('../../utils/validation');
const {
  updateUserData,
  checkIdByEmailOrUserName,
} = require('../../database/queries');

const updateProfileData = async (req, res, next) => {
  try {
    const { userId } = req;

    const validateData = await profileDataSchema.validate(req.body, {
      abortEarly: false,
    });

    if (validateData.email) {
      const {
        rows: [userByEmail],
      } = await checkIdByEmailOrUserName({ email: validateData.email });

      if (userByEmail) {
        return next(boom.conflict('The email already exists'));
      }
    }

    if (validateData.userName) {
      const {
        rows: [userByUserName],
      } = await checkIdByEmailOrUserName({ userName: validateData.userName });

      if (userByUserName) {
        return next(boom.conflict('The userName already exists'));
      }
    }
    if (validateData.password) {
      const hashedPassword = await hash(validateData.password, 10);
      req.body.password = hashedPassword;
    }

    const {
      rows: [profileData],
    } = await updateUserData({
      ...req.body,
      userId,
    });

    return res.status(201).json({
      title: 'User profile data update',
      detail: 'Data Successfully updated',
      profileData: [
        {
          userName: profileData.user_name,
          firstName: profileData.first_name,
          lastName: profileData.last_name,
          email: profileData.email,
          image: profileData.image,
          mobile: profileData.mobile,
          isAdmin: profileData.is_admin,
        },
      ],
    });
  } catch (error) {
    return next(
      error.name === 'ValidationError'
        ? boom.badRequest(error.errors)
        : boom.badImplementation(error)
    );
  }
};

module.exports = updateProfileData;
