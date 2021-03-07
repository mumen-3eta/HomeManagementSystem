const boom = require('@hapi/boom');
const { hash } = require('bcrypt');

const { registerUserSchema } = require('../../utils/validation');
const {
  checkIdByEmailOrUserName,
  registerUser,
} = require('../../database/queries');
const { sign } = require('../../utils/jwt');

const addUsers = async (req, res, next) => {
  try {
    const { userName, email, password } = await registerUserSchema.validate(
      req.body,
      {
        abortEarly: false,
      }
    );

    // console.log(user);
    const {
      rows: [userByEmail],
    } = await checkIdByEmailOrUserName({ email });

    if (userByEmail) {
      return next(boom.conflict('The email already exists'));
    }

    const {
      rows: [userByUserName],
    } = await checkIdByEmailOrUserName({ userName });

    if (userByUserName) {
      return next(boom.conflict('The userName already exists'));
    }
    const hashedPassword = await hash(password, 10);

    const {
      rows: [{ id: userId, is_admin: isAdmin }],
    } = await registerUser({ userName, email, password: hashedPassword });

    const token = await sign({ userId, isAdmin });
    return res
      .cookie('token', token, {
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
      })
      .status(201)
      .json({
        title: 'User Registration',
        detail: 'Successfully registered new user',
      });
  } catch (error) {
    return next(
      error.name === 'ValidationError'
        ? boom.badRequest(error.errors)
        : boom.badImplementation(error)
    );
  }
};

module.exports = addUsers;
