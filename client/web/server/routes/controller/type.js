const router = require('express').Router();
// const { ControllerType } = require('../../models/processor');
// const { boomify } = require('../../utils');const {
const {
  addControllerTypeLabel,
  updateControllerTypeLabel,
  deleteControllerTypeLabel,
  getAllControllerTypes,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.get('/', getAllControllerTypes);
router.use(protectedAdmin);
router.post('/', addControllerTypeLabel);
router.put('/', updateControllerTypeLabel);
router.delete('/', deleteControllerTypeLabel);

module.exports = router;
