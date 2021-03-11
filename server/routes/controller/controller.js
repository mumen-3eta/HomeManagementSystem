const router = require('express').Router();

const {
  createControllerForProduction,
  connectControllerToProcessor,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.post('/create', protectedAdmin, createControllerForProduction);
router.post('/connection', connectControllerToProcessor);

router.get('/connection', getAllProcessorControllers);
router.get('/connection/all', getAllUserControllers);
router.delete('/connection', deleteControllerConnection);

module.exports = router;
