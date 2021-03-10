const boom = require('@hapi/boom');

const {
  addControllerType,
  getControllerTypeByLabel,
} = require('../../../database/queries');

const addControllerTypeLabel = async (req, res, next) => {
  try {
    const { label } = req.body;

    const {
      rows: [typeData],
    } = await getControllerTypeByLabel({ typeLabel: label });

    if (typeData) {
      return next(boom.conflict('this type is already there'));
    }

    const { rows: data } = await addControllerType({ label });

    return res.status(201).json({
      title: 'adding Controller Type',
      detail: 'Controller Type added Successfully',
      data,
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = addControllerTypeLabel;
