const boom = require('@hapi/boom');
const { hash } = require('bcrypt');

const {
  //   registerUserSchema,
  profileDataSchema,
} = require('../../utils/validation');
const {
  updateUserData,
  checkIdByEmailOrUserName,
} = require('../../database/queries');

const updateProfileData = async (req, res, next) => {
  try {
    // dont forget to un comment the next line later---------------------------------------------------
    // const { userId } = req;
    const userId = 1;

    const { userName, email, password } = await profileDataSchema.validate(
      req.body,
      {
        abortEarly: false,
      }
    );

    if (email) {
      const {
        rows: [userByEmail],
      } = await checkIdByEmailOrUserName({ email });

      if (userByEmail) {
        return next(boom.conflict('The email already exists'));
      }
    }

    if (userName) {
      const {
        rows: [userByUserName],
      } = await checkIdByEmailOrUserName({ userName });

      if (userByUserName) {
        return next(boom.conflict('The userName already exists'));
      }
    }
    if (password) {
      const hashedPassword = await hash(password, 10);
      req.body.password = hashedPassword;
    }

    const { rows: profileData } = await updateUserData({
      ...req.body,
      userId,
    });

    return res.status(201).json({
      title: 'User profile data update',
      detail: 'Data Successfully updated',
      profileData,
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
