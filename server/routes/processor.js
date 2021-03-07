const router = require('express').Router();
// const {
//   createProcessorProduction,
//   connectProcessorUser,
//   getProcessorConnectionByUserId,
//   deleteProcessorConnection,
// } = require('../database/queries');
// const { boomify } = require('../utils');
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

// router.delete('/user/processor', authenticate, async (req, res, next) => {
//   try {
//     const { userId } = req.session;
//     const { ProcessorId } = req.body;

//     await deleteProcessorConnection(userId, ProcessorId);

//     res.json({
//       title: 'processor delete Successful',
//     });
//   } catch (err) {
//     next(boomify(400, 'processor delete Error', err.message));
//   }
// });

module.exports = router;
