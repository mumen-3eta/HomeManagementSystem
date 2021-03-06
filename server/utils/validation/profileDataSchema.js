const { object, string, number } = require('yup');

const registerUserSchema = object({
  userName: string().min(5).max(20),
  email: string().email(),
  password: string().min(8).max(50),
  firstName: string().min(1).max(20),
  lastName: string().min(1).max(20),
  image: string(),
  mobile: number().min(10).max(15),
});

module.exports = registerUserSchema;
