const router = require('express').Router();

const usersRoute = require('./users');
const { swaggerServe, swaggerSetup } = require('../controllers');
// const processorRoute = require('./processor');
// const controllerRoute = require('./controller');

router.use('/api-docs', swaggerServe, swaggerSetup);
router.use('/users', usersRoute);
// router.use('/', processorRoute);
// router.use('/', controllerRoute);

module.exports = router;
