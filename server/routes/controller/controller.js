const router = require('express').Router();

const {
  createControllerForProduction,
  getAllControllerProduction,
  connectControllerToProcessor,
  changeControllerStatus,
  getAllProcessorControllers,
  getAllUserControllers,
  deleteControllerConnection,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.post('/create', protectedAdmin, createControllerForProduction);
router.get('/all', protectedAdmin, getAllControllerProduction);

router.post('/connection', connectControllerToProcessor);
router.post('/change', changeControllerStatus );
router.post('/connection/processor', getAllProcessorControllers);
router.get('/connection/all', getAllUserControllers);
router.delete('/connection', deleteControllerConnection);

module.exports = router;
