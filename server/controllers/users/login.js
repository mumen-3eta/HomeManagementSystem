const boom = require('@hapi/boom');
const { compare } = require('bcrypt');

const { loginSchema } = require('../../utils/validation');
const { checkIdByEmailOrUserName } = require('../../database/queries');
const { sign } = require('../../utils/jwt');

const Login = async (req, res, next) => {
  try {
    const { userName, email, password } = await loginSchema.validate(req.body, {
      abortEarly: false,
    });
    if (!email && !userName) {
      return next(boom.conflict('No email or user name entered !!!'));
    }

    const {
      rows: [user],
    } = await checkIdByEmailOrUserName({ email, userName });
    if (!user) {
      return next(boom.unauthorized('Invalid Email/Password'));
    }

    const { id: userId, password: userPassword, is_admin: isAdmin } = user;
    const hashedPassword = await compare(password, userPassword);

    if (!hashedPassword) {
      return next(boom.unauthorized('Invalid Email/Password'));
    }
    const token = await sign({ userId, isAdmin });
    return res
      .cookie('token', token, {
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
      })
      .status(201)
      .send({
        title: 'User login',
        detail: 'Successfully loged-in ',
      });
  } catch (error) {
    return next(
      error.name === 'ValidationError'
        ? boom.badRequest(error.errors)
        : boom.badImplementation(error)
    );
  }
};

module.exports = Login;
