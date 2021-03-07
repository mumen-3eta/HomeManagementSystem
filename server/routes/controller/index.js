const router = require('express').Router();

const controllerRoute = require('./controller');
// const locationRoute = require('./location');
const typeRoute = require('./type');

router.use('/', controllerRoute);
router.use('/type', typeRoute);
// router.use('/location', locationRoute);locationRoute

module.exports = router;
