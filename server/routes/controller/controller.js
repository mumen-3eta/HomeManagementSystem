const router = require('express').Router();

const {
  createControllerForProduction,
  getAllControllerProduction,
  connectControllerToProcessor,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.post('/create', protectedAdmin, createControllerForProduction);
router.get('/all', protectedAdmin, getAllControllerProduction);
router.post('/connection', connectControllerToProcessor);

router.post('/connection', getAllProcessorControllers);
router.get('/connection/all', getAllUserControllers);
router.delete('/connection', deleteControllerConnection);

module.exports = router;
