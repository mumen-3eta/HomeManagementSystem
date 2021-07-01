const boom = require('@hapi/boom');

const {
  deleteControllerLocation,
  getControllerLocation,
} = require('../../../database/queries');

const deleteControllerLocationLabel = async (req, res, next) => {
  try {
    const { locationId } = req.body;
    const {
      rows: [locationIdData],
    } = await getControllerLocation({ locationId });

    if (!locationIdData) {
      return next(boom.conflict('this location id is not valid'));
    }

    await deleteControllerLocation({ locationId });

    return res.status(201).json({
      title: 'deleting Controller location',
      detail: 'Controller location deleted Successfully',
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = deleteControllerLocationLabel;
