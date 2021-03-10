const router = require('express').Router();

const {
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
} = require('../controllers');
const { protectedAdmin } = require('../middleware/authentication');

router.post('/create', protectedAdmin, createProcessorForProduction);
router.get('/connection', getProcessorConnection);
router.post('/connection', connectProcessorToUser);
router.delete('/connection', deleteProcessorConnection);

module.exports = router;
