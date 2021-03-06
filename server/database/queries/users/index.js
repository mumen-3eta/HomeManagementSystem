const registerUser = require('./registerUser');
const checkIdByEmailOrUserName = require('./checkIdByEmailOrUserName');
const getUserDataById = require('./getUserDataById');
const deleteUserById = require('./deleteUserById');
const getAllUserData = require('./getAllUserData');
const updateUserData = require('./updateUserData');

module.exports = {
  registerUser,
  checkIdByEmailOrUserName,
  getUserDataById,
  deleteUserById,
  getAllUserData,
  updateUserData,
};
