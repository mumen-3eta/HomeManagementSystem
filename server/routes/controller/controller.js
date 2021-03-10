const router = require('express').Router();

const {
  createControllerForProduction,
  connectControllerToProcessor,
} = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.post('/create', protectedAdmin, createControllerForProduction);
router.post('/connection', connectControllerToProcessor);

// router.get('/connection', );
// router.delete('/connection', );

module.exports = router;
