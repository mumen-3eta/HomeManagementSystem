const router = require('express').Router();
// const {
//   UserController,
//   ControllerType,
//   ControllerLocation,
//   ControllerProduction,
// } = require('../../models/controller');
const {
  createControllerProduction,
  connectControllerProcessor,
  updateUserControllerData,
  deleteControllerConnection,
  addControllerType,
  updateControllerType,
  deleteControllerType,
  getControllerType,
  addControllerLocation,
  getControllerLocation,
  updateControllerLocation,
  deleteControllerLocation,
} = require('../../database/queries');
const { boomify } = require('../../utils');
const { authenticate } = require('../../middleware/authenticate');

// creat controller as an admin
router.post('/controller', async (req, res, next) => {
  try {
    // const controller = new ControllerProduction();
    // const { _id: controllerId } = await controller.save();
    const { rows: controllerId } = createControllerProduction();

    res.json({
      title: 'controller Production add Successful',
      controllerId,
    });
  } catch (err) {
    next(boomify(400, 'controller Production add Error', err.message));
  }
});

// update controller as a user
router.post('/user/controller', authenticate, async (req, res, next) => {
  try {
    // {name,ProcessorId,typeId,locationId,controllerId}

    // check if he the owner

    // const userController = new UserController(req.body);
    // const data = await userController.save();
    const { rows: data } = await connectControllerProcessor(req.body);
    res.json({
      title: 'controller add Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'controller Production add Error', err.message));
  }
});

// no select query ?????**************************

router.put('/user/controller', authenticate, async (req, res, next) => {
  try {
    // {userControllerID,name,ProcessorId,typeId,locationId,controllerId}

    // check if he the owner
    // const data = await UserController.findOneAndUpdate(
    //   { _id: userControllerID },
    //   req.body,
    //   { upsert: true, new: true }
    // );
    const { rows: data } = await updateUserControllerData(req.body);
    res.json({
      title: 'controller add Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'controller Production add Error', err.message));
  }
});

// delete
router.delete('/user/controller', authenticate, async (req, res, next) => {
  try {
    const { userControllerID } = req.body;

    // check if he the owner

    // await UserController.findByIdAndDelete({
    //   _id: userControllerID,
    // });
    await deleteControllerConnection(userControllerID);

    res.json({
      title: 'User Controller delete Successful',
    });
  } catch (err) {
    next(boomify(400, 'User Controller add Error', err.message));
  }
});

// ** type **
router.post('/controller/type', async (req, res, next) => {
  try {
    // label
    console.log('asdsa');
    // const newControllerType = new ControllerType(req.body);
    // const data = await newControllerType.save();
    const { rows: data } = await addControllerType(req.body.label);

    res.json({
      title: 'Controller Type add Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'Controller Type add Error', err.message));
  }
});

router.put('/controller/type', async (req, res, next) => {
  try {
    // labelId,label
    const { labelId, label } = req.body;
    // const data = await ControllerType.findOneAndUpdate(
    //   { _id: labelId },
    //   { label },
    //   { upsert: true, new: true }
    // );
    const { rows: data } = updateControllerType(labelId, label);

    res.json({
      title: 'Controller Type update Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'Controller Type update Error', err.message));
  }
});

router.delete('/controller/type', async (req, res, next) => {
  try {
    // await ControllerType.findOneAndDelete({ _id: labelId });

    await deleteControllerType(req.body.labelId);

    res.json({
      title: 'Controller Type delete Successful',
    });
  } catch (err) {
    next(boomify(400, 'Controller Type delete Error', err.message));
  }
});

// eslint-disable-next-line consistent-return
router.get('/controller/type', async (req, res, next) => {
  try {
    // const data = await ControllerType.find({});
    const { rows: data } = await getControllerType();

    return res.json({
      data: data.map((item) => ({
        labelId: item.id,
        label: item.label,
      })),
    });
  } catch (err) {
    next(boomify(401, 'Invalid Credentials', err.message));
  }
});

// ** location **
router.post('/controller/location', async (req, res, next) => {
  try {
    // label
    // const newControllerLocation = new ControllerLocation(req.body);
    // const data = await newControllerLocation.save();
    const { rows: data } = await addControllerLocation(req.body.label);

    res.json({
      title: 'Controller location add Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'Controller location add Error', err.message));
  }
});

router.put('/controller/location', async (req, res, next) => {
  try {
    // labelId,label
    const { locationId, label } = req.body;
    // const data = await ControllerLocation.findOneAndUpdate(
    //   { _id: locationId },
    //   { label },
    //   { upsert: true, new: true }
    // );
    const { rows: data } = await updateControllerLocation(locationId, label);

    res.json({
      title: 'Controller location update Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'Controller location update Error', err.message));
  }
});

router.delete('/controller/location', async (req, res, next) => {
  try {
    // await ControllerType.ControllerLocation({ _id: locationId });

    await deleteControllerLocation(req.body.locationId);

    res.json({
      title: 'Controller location delete Successful',
    });
  } catch (err) {
    next(boomify(400, 'Controller location delete Error', err.message));
  }
});

// eslint-disable-next-line consistent-return
router.get('/controller/location', async (req, res, next) => {
  try {
    // const data = await ControllerLocation.find({});

    const { rows: data } = await getControllerLocation();

    return res.json({
      data: data.map((item) => ({
        // locationId: item._id,
        locationId: item.id,
        label: item.label,
      })),
    });
  } catch (err) {
    next(boomify(401, 'Invalid Credentials', err.message));
  }
});

module.exports = router;
