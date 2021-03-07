const router = require('express').Router();

const {
  createProcessorForProduction,
  connectProcessorToUser,
  getProcessorConnection,
  deleteProcessorConnection,
} = require('../controllers');

router.post('/create', createProcessorForProduction);
router.get('/connection', getProcessorConnection);
router.post('/connection', connectProcessorToUser);
router.delete('/connection', deleteProcessorConnection);

module.exports = router;
