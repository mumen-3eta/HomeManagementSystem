const boom = require('@hapi/boom');
const { hash } = require('bcrypt');

const registerUserSchema = require('../../utils/validation');
const { checkIdByEmail, registerUser } = require('../../database/queries');
const { sign } = require('../../utils/jwt');

const addUsers = async (req, res, next) => {
  try {
    const { userName, email, password } = await registerUserSchema.validate(
      req.body,
      {
        abortEarly: false,
      }
    );

    const {
      rows: [user],
    } = await checkIdByEmail({ email });
    if (user) {
      return next(boom.conflict('The email is already exists'));
    }
    const hashedPassword = await hash(password, 10);

    const { rows: data } = await registerUser(userName, email, hashedPassword);
    const { id: userId } = data;

    const token = await sign({ userId });
    return res
      .cookie('token', token, {
        maxAge: 1000 * 60 * 60 * 24 * 7 * 10,
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
      })
      .status(201)
      .send({
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
