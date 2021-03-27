/* eslint-disable no-underscore-dangle */
const router = require('express').Router();

const { getAllProfileData, updateProfileData, getAllUsers } = require('../../controllers');

const { protectedAdmin } = require('../../middleware/authentication');

router.get('/', getAllProfileData);
router.put('/', updateProfileData);
router.get('/all', protectedAdmin, getAllUsers);


module.exports = router;
