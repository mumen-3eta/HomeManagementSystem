const router = require('express').Router();

const controllerRoute = require('./controller');
const typeRoute = require('./type');
const locationRoute = require('./location');

router.use('/', controllerRoute);
router.use('/type', typeRoute);
router.use('/location', locationRoute);

module.exports = router;
