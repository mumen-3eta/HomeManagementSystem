const signUp = require('./registerUser');
const signIn = require('./checkIdByEmail');
const getUserDataById = require('./getUserDataById');
const deleteUser = require('./deleteUserById');
const getAllUserData = require('./getAllUserData');
const updateUserData = require('./updateUserData');

module.exports = {
  signUp,
  signIn,
  getUserDataById,
  deleteUser,
  getAllUserData,
  updateUserData,
};
