const router = require('express').Router();

const {
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
} = require('../controllers');

router.post('/create', createProcessorForProduction);
router.post('/connection', connectProcessorToUser);
router.get('/connection', getProcessorConnection);
router.delete('/connection', deleteProcessorConnection);

module.exports = router;
