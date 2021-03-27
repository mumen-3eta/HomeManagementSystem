const router = require('express').Router();

const {
  addControllerLocationLabel,
  updateControllerLocationLabel,
  deleteControllerLocationLabel,
  getAllControllerLocations,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.get('/', getAllControllerLocations);
router.use(protectedAdmin);
router.put('/', updateControllerLocationLabel);
router.delete('/', deleteControllerLocationLabel);
router.post('/', addControllerLocationLabel);

module.exports = router;
