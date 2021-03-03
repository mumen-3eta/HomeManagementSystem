/* eslint-disable no-underscore-dangle */
const router = require('express').Router();
// const User = require('../../models/user');
const { boomify } = require('../../utils');
const { csrfCheck } = require('../../middleware/csrfCheck');
const { getAllUserData, updateUserData } = require('../../database/queries');

router.get('/', async (req, res, next) => {
  try {
    const { userId } = req.session;
    // const data = await User.findById({ _id: userId });
    const data = await getAllUserData(userId);
    delete data.basicInfo.createdAt;
    res.json({ statusCode: 200, data });
  } catch (err) {
    next(boomify(401, 'Not authorized to access this route', err.message));
  }
});

router.post('/', csrfCheck, async (req, res, next) => {
  try {
    console.log(req.body);
    const { userId } = req.session;
    console.log(userId);
    // const data = await User.findOneAndUpdate(
    //   { _id: userId },
    //   { $set: req.body },
    //   { upsert: true, new: true }
    // );
    const data = await updateUserData(userId, req.body);
    console.log(data);
    res.status(200).json({
      statusCode: 200,
      data,
    });
  } catch (err) {
    next(boomify(400, 'Registration Error', err.message));
  }
});

module.exports = router;
