const router = require('express').Router();

const {
  addControllerLocationLabel,
  updateControllerLocationLabel,
  deleteControllerLocationLabel,
  getAllControllerLocations,
} = require('../../controllers');

router.post('/', addControllerLocationLabel);
router.put('/', updateControllerLocationLabel);
router.delete('/', deleteControllerLocationLabel);
router.get('/', getAllControllerLocations);

module.exports = router;
