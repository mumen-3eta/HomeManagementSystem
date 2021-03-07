const router = require('express').Router();

const {
  createControllerForProduction,
  connectControllerToProcessor,
} = require('../../controllers');

router.post('/create', createControllerForProduction);
router.post('/connection', connectControllerToProcessor);

// ******************************************************************************************************************************************
// // no select query ?????**************************

// router.put('/user/controller', authenticate, async (req, res, next) => {
//   try {
//     // {userControllerID,name,ProcessorId,typeId,locationId,controllerId}

//     // check if he the owner

//     const { rows: data } = await updateUseurControllerData(req.body);
//     res.json({
//       title: 'controller add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'controller Production add Error', err.message));
//   }
// });

// // delete
// router.delete('/user/controller', authenticate, async (req, res, next) => {
//   try {
//     const { userControllerID } = req.body;

//     // check if he the owner

//     await deleteControllerConnection(userControllerID);

//     res.json({
//       title: 'User Controller delete Successful',
//     });
//   } catch (err) {
//     next(boomify(400, 'User Controller add Error', err.message));
//   }
// });

module.exports = router;
