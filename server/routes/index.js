const router = require('express').Router();

const usersRoute = require('./users');
const processorRoute = require('./processor');
const controllerRoute = require('./controller');

router.use('/users', usersRoute);
router.use('/', processorRoute);
router.use('/', controllerRoute);

module.exports = router;
