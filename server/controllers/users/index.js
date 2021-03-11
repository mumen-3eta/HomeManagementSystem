const addUsers = require('./addUsers');
const authUser = require('./authUser');
const login = require('./login');
const getAllProfileData = require('./getAllProfileData');
const updateProfileData = require('./updateProfileData');
const logout = require('./logout');

module.exports = {
  addUsers,
  login,
  authUser,
  getAllProfileData,
  updateProfileData,
  logout,
};
