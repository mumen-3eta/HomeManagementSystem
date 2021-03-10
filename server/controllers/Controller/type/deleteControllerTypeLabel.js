const boom = require('@hapi/boom');

const {
  deleteControllerType,
  getControllerType,
} = require('../../../database/queries');

const deleteControllerTypeLabel = async (req, res, next) => {
  try {
    const { typeId } = req.body;
    const {
      rows: [typeIdData],
    } = await getControllerType({ typeId });

    if (!typeIdData) {
      return next(boom.conflict('this type id is not valid'));
    }

    await deleteControllerType({ typeId });

    return res.status(201).json({
      title: 'deleting Controller Type',
      detail: 'Controller Type deleted Successfully',
    });
  } catch (error) {
    return next(boom.badImplementation(error));
  }
};

module.exports = deleteControllerTypeLabel;
