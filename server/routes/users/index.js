const router = require('express').Router();

// const { authenticate } = require('../../middleware/authenticate');
const usersRoute = require('./users');
const profileRoute = require('./profile');

router.use('/', usersRoute);
router.use('/profile', profileRoute);

module.exports = router;
