const router = require('express').Router();
const {
    UserController,
    ControllerType,
    ControllerLocation,
    ControllerProduction,
} = require('../../models/controller');
const {boomify} = require('../../utils');
const {authenticate} = require('../../middleware/authenticate');

// creat controller as an admin
router.post('/controller', async (req, res, next) => {
    try {
        const controller = new ControllerProduction();
        const {_id: controllerId} = await controller.save();

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
        //{name,ProcessorId,typeId,locationId,controllerId}

        // check if he the owner

        const userController = new UserController(req.body);
        const data = await userController.save();
        res.json({
            title: 'controller add Successful',
            data,
        });
    } catch (err) {
        next(boomify(400, 'controller Production add Error', err.message));
    }
});

//
router.put('/user/controller', authenticate, async (req, res, next) => {
    try {
        //{userControllerID,name,ProcessorId,typeId,locationId,controllerId}

        // check if he the owner
        const data = await UserController.findOneAndUpdate(
            {_id: req.body.userControllerID},
            req.body,
            {upsert: true, new: true}
        );

        res.json({
            title: 'controller add Successful',
            data,
        });
    } catch (err) {
        next(boomify(400, 'controller Production add Error', err.message));
    }
});

// delet
router.delete('/user/controller', authenticate, async (req, res, next) => {
    try {
        //{userControllerID}

        // check if he the owner

        await UserController.findByIdAndDelete({
            _id: userControllerID,
        });

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
        const newControllerType = new ControllerType(req.body);
        const data = await newControllerType.save();

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
        const {labelId, label} = req.body;
        const data = await ControllerType.findOneAndUpdate(
            {_id: labelId},
            {label},
            {upsert: true, new: true}
        );
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
        const {labelId} = req.body;
        await ControllerType.findOneAndDelete({_id: labelId});
        res.json({
            title: 'Controller Type delete Successful',
        });
    } catch (err) {
        next(boomify(400, 'Controller Type delete Error', err.message));
    }
});

router.get('/controller/type', async (req, res, next) => {
    try {
        const data = await ControllerType.find({});

        return res.json({
            data: data.map((item) => ({
                labelId: item._id,
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
        const newControllerLocation = new ControllerLocation(req.body);
        const data = await newControllerLocation.save();

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
        const {locationId, label} = req.body;
        const data = await ControllerLocation.findOneAndUpdate(
            {_id: locationId},
            {label},
            {upsert: true, new: true}
        );
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
        const {locationId} = req.body;
        await ControllerType.findOneAndDelete({_id: locationId});

        res.json({
            title: 'Controller location delete Successful',
        });
    } catch (err) {
        next(boomify(400, 'Controller location delete Error', err.message));
    }
});

router.get('/controller/location', async (req, res, next) => {
    try {
        const data = await ControllerLocation.find({});

        return res.json({
            data: data.map((item) => ({
                locationId: item._id,
                label: item.label,
            })),
        });
    } catch (err) {
        next(boomify(401, 'Invalid Credentials', err.message));
    }
});

module.exports = router;
