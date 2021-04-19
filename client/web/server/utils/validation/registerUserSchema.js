const { object, string } = require('yup');

const registerUserSchema = object({
  userName: string().min(5).max(20).required(),
  email: string().email().required(),
  password: string().min(8).max(50).required(),
});

module.exports = registerUserSchema;
