const addUsers = require('./addUsers');
const authUser = require('./authUser');
const login = require('./login');
const logout = require('./logout')
const getAllUsers = require('./getAllUsers')
const getAllProfileData = require('./getAllProfileData');
const updateProfileData = require('./updateProfileData');

module.exports = {
  addUsers,
  login,
  authUser,
  getAllUsers,
  getAllProfileData,
  updateProfileData,
  logout,
};
