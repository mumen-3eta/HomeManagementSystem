const router = require('express').Router();

const usersRoute = require('./users');
const profileRoute = require('./profile');

const { verifyUser } = require('../../middleware/authentication');

router.use('/', usersRoute);
router.use('/profile', verifyUser, profileRoute);

module.exports = router;
