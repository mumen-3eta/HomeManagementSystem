const { object, string } = require('yup');

const loginSchema = object({
  userName: string().min(5).max(20),
  email: string().email(),
  password: string().min(8).max(50).required(),
});

module.exports = loginSchema;
