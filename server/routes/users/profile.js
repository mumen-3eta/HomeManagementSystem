/* eslint-disable no-underscore-dangle */
const router = require('express').Router();

const { getAllProfileData, updateProfileData } = require('../../controllers');

router.get('/', getAllProfileData);
router.put('/', updateProfileData);

module.exports = router;
