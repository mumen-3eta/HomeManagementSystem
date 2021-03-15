const addUsers = require('./addUsers');
const authUser = require('./authUser');
const login = require('./login');
const logout = require('./logout')
const getAllProfileData = require('./getAllProfileData');
const updateProfileData = require('./updateProfileData');

module.exports = {
  addUsers,
  login,
  authUser,
  getAllProfileData,
  updateProfileData,
  logout,
};
