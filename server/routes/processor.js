const router = require('express').Router();
const {
  createProcessorProduction,
  connectProcessorUser,
  getProcessorConnectionByUserId,
  deleteProcessorConnection,
} = require('../database/queries');
// const { Processor, ProcessorProduction } = require('../models/processor');
const { boomify } = require('../utils');

router.post('/processor/create', async (req, res, next) => {
  try {
    // const newProcessor = new ProcessorProduction();
    // const { _id: processorId } = await newProcessor.save();
    const { rows: processorId } = await createProcessorProduction;

    res.json({
      title: 'processor Production add Successful',
      processorId,
    });
  } catch (err) {
    next(boomify(400, 'processor Production add Error', err.message));
  }
});

router.post('/user/processor', authenticate, async (req, res, next) => {
  try {
    const { userId } = req.session;

    // const processor = new Processor({ ...req.body, userId });
    // const data = await processor.save();
    const { rows: data } = await connectProcessorUser(
      userId,
      req.body.processorId
    );
    res.json({
      title: 'processor add Successful',
      data,
    });
  } catch (err) {
    next(boomify(400, 'processor add Error', err.message));
  }
});

// eslint-disable-next-line consistent-return
router.get('/user/processor', authenticate, async (req, res, next) => {
  try {
    const { userId } = req.session;

    const { rows: data } = await getProcessorConnectionByUserId(userId);

    return res.json({
      data: data.map((item) => item.ProcessorId),
    });
  } catch (err) {
    next(boomify(401, 'Invalid Credentials', err.message));
  }
});

router.delete('/user/processor', authenticate, async (req, res, next) => {
  try {
    const { userId } = req.session;
    const { ProcessorId } = req.body;

    // await Processor.findOneAndDelete({ userId, ProcessorId });
    await deleteProcessorConnection(userId, ProcessorId);

    res.json({
      title: 'processor delete Successful',
    });
  } catch (err) {
    next(boomify(400, 'processor delete Error', err.message));
  }
});

module.exports = router;
