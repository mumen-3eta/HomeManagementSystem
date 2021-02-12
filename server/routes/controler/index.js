const router = require('express').Router();

const controlerRoute = require('./controler');
const locationRoute = require('./location');
const typeRoute = require('./type');

router.use('/', controlerRoute);
router.use('/type', locationRoute);
router.use('/location', typeRoute);

module.exports = router;
