const router = require('express').Router();
const { ControllerType } = require('../../models/processor');
const { boomify } = require('../../utils');

// router.post('/', async (req, res, next) => {
//   try {
//     // label
//     console.log('asdsa');
//     const newControllerType = new ControllerType(req.body);
//     const data = await newControllerType.save();

//     res.json({
//       title: 'Controller Type add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller Type add Error', err.message));
//   }
// });

// router.put('/', async (req, res, next) => {
//   try {
//     // labelId,label
//     const { labelId, label } = req.body;
//     const data = await ControllerType.findOneAndUpdate(
//       { _id: labelId },
//       { label },
//       { upsert: true, new: true }
//     );
//     res.json({
//       title: 'Controller Type add Successful',
//       data,
//     });
//   } catch (err) {
//     next(boomify(400, 'Controller Type add Error', err.message));
//   }
// });

// router.delete('/', async (req, res, next) => {});

module.exports = router;