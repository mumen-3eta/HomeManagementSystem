const router = require('express').Router();

// const {
//   createControllerProduction,
//   connectControllerProcessor,
//   updateUserControllerData,
//   deleteControllerConnection,
//   addControllerType,
//   updateControllerType,
//   deleteControllerType,
//   getControllerType,
//   addControllerLocation,
//   getControllerLocation,
//   updateControllerLocation,
//   deleteControllerLocation,
// } = require('../../database/queries');
const {
  createControllerForProduction,
  connectControllerToProcessor,
  // connectProcessorToUser,
  // getProcessorConnection,
  // deleteProcessorConnection,
} = require('../../controllers');
// const { boomify } = require('../../utils');
// const { authenticate } = require('../../middleware/authenticate');
router.post('/create', createControllerForProduction);
router.post('/connection', connectControllerToProcessor);
// router.get('/connection', getProcessorConnection);

// creat controller as an admin
// router.post('/controller', async (req, res, next) => {
//   try {
//     const { rows: controllerId } = createControllerProduction();

//     res.json({
//       title: 'controller Production add Successful',
//       controllerId,
//     });
//   } catch (err) {
//     next(boomify(400, 'controller Production add Error', err.message));
//   }
// });

// // update controller as a user
// router.post('/user/controller', authenticate, async (req, res, next) => {
//   try {
//     // {name,ProcessorId,typeId,locationId,controllerId}

//     // check if he the owner

//     const { rows: data } = await connectControllerProcessor(req.body);
//     res.json({
//       title: 'controller add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'controller Production add Error', err.message));
//   }
// });

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

// // ** type **
// router.post('/controller/type', async (req, res, next) => {
//   try {
//     // label
//     console.log('asdsa');

//     const { rows: data } = await addControllerType(req.body.label);

//     res.json({
//       title: 'Controller Type add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller Type add Error', err.message));
//   }
// });

// router.put('/controller/type', async (req, res, next) => {
//   try {
//     // labelId,label
//     const { labelId, label } = req.body;

//     const { rows: data } = updateControllerType(labelId, label);

//     res.json({
//       title: 'Controller Type update Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller Type update Error', err.message));
//   }
// });

// router.delete('/controller/type', async (req, res, next) => {
//   try {
//     await deleteControllerType(req.body.labelId);

//     res.json({
//       title: 'Controller Type delete Successful',
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller Type delete Error', err.message));
//   }
// });

// // eslint-disable-next-line consistent-return
// router.get('/controller/type', async (req, res, next) => {
//   try {
//     const { rows: data } = await getControllerType();

//     return res.json({
//       data: data.map((item) => ({
//         labelId: item.id,
//         label: item.label,
//       })),
//     });
//   } catch (err) {
//     next(boomify(401, 'Invalid Credentials', err.message));
//   }
// });

// // ** location **
// router.post('/controller/location', async (req, res, next) => {
//   try {
//     // label
//     const { rows: data } = await addControllerLocation(req.body.label);

//     res.json({
//       title: 'Controller location add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller location add Error', err.message));
//   }
// });

// router.put('/controller/location', async (req, res, next) => {
//   try {
//     // labelId,label
//     const { locationId, label } = req.body;

//     const { rows: data } = await updateControllerLocation(locationId, label);

//     res.json({
//       title: 'Controller location update Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller location update Error', err.message));
//   }
// });

// router.delete('/controller/location', async (req, res, next) => {
//   try {
//     await deleteControllerLocation(req.body.locationId);

//     res.json({
//       title: 'Controller location delete Successful',
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller location delete Error', err.message));
//   }
// });

// // eslint-disable-next-line consistent-return
// router.get('/controller/location', async (req, res, next) => {
//   try {
//     const { rows: data } = await getControllerLocation();

//     return res.json({
//       data: data.map((item) => ({
//         locationId: item.id,
//         label: item.label,
//       })),
//     });
//   } catch (err) {
//     next(boomify(401, 'Invalid Credentials', err.message));
//   }
// });

module.exports = router;
