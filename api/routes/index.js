const router = require('express').Router();

const usersRoute = require('./users');
const processorRoute = require('./processor');
const controlerRoute = require('./controler');

router.use('/users', usersRoute);
router.use('/processor', processorRoute);
router.use('/controler', controlerRoute);

module.exports = router;
