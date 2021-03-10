const boom = require('@hapi/boom');

const {
  addControllerLocation,
  getControllerLocationByLabel,
} = require('../../../database/queries');

const addControllerTypeLabel = async (req, res, next) => {
  try {
    const { label } = req.body;

    const {
      rows: [locationData],
    } = await getControllerLocationByLabel({ locationLabel: label });

    if (locationData) {
      return next(boom.conflict('this location is already there'));
    }

    const { rows: data } = await addControllerLocation({ label });

    return res.status(201).json({
      title: 'adding Controller location',
      detail: 'Controller location added Successfully',
      data,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = addControllerTypeLabel;
