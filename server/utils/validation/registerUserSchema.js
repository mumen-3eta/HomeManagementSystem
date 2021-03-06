const { object, string } = require('yup');

const registerUserSchema = object({
  userName: string().min(5).required(),
  email: string().email().required(),
  password: string().min(8).required(),
});

module.exports = registerUserSchema;
