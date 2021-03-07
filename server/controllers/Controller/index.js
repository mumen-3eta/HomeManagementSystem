const {
  createControllerForProduction,
  connectControllerToProcessor,
} = require('./unit');

const {
  addControllerTypeLabel,
  updateControllerTypeLabel,
  deleteControllerTypeLabel,
} = require('./type');

module.exports = {
  createControllerForProduction,
  connectControllerToProcessor,
  addControllerTypeLabel,
  updateControllerTypeLabel,
  deleteControllerTypeLabel,
};
