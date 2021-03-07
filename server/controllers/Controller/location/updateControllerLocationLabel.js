const boom = require('@hapi/boom');

const {
  updateControllerLocation,
  getControllerLocationByLabel,
  getControllerLocation,
} = require('../../../database/queries');

const updateControllerLocationLabel = async (req, res, next) => {
  try {
    const { label, locationId } = req.body;
    const {
      rows: [locationData],
    } = await getControllerLocationByLabel({ locationLabel: label });

    if (locationData) {
      return next(boom.conflict('this location is already there'));
    }
    const {
      rows: [locationIdData],
    } = await getControllerLocation({ locationId });

    if (!locationIdData) {
      return next(boom.conflict('this location id is not valid'));
    }

    const { rows: data } = await updateControllerLocation({
      label,
      locationId,
    });

    return res.status(201).json({
      title: 'updating Controller location',
      detail: 'Controller location renamed Successfully',
      data,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = updateControllerLocationLabel;
