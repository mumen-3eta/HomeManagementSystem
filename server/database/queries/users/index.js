const registerUser = require('./registerUser');
const checkIdByEmail = require('./checkIdByEmail');
const getUserDataById = require('./getUserDataById');
const deleteUserById = require('./deleteUserById');
const getAllUserData = require('./getAllUserData');
const updateUserData = require('./updateUserData');

module.exports = {
  registerUser,
  checkIdByEmail,
  getUserDataById,
  deleteUserById,
  getAllUserData,
  updateUserData,
};
