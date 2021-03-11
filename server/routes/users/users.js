const router = require('express').Router();
const { addUsers, login, authUser, logout } = require('../../controllers');
const { verifyUser } = require('../../middleware/authentication');

router.post('/register', addUsers);
router.post('/login', login);
router.get('/me', verifyUser, authUser);
router.put('/logout', logout);

// // eslint-disable-next-line no-unused-vars
// router.delete('/me', authenticate, csrfCheck, async (req, res, next) => {
//   try {
//     const { userId } = req.session;
//     const { password } = req.body;
//     if (typeof password !== 'string') {
//       throw new Error();
//     }
//     // const user = await User.findById({ _id: userId });
//     const user = await signIn(userId);

//     const passwordValidated = await bcrypt.compare(
//       password,
//       // user.basicInfo.password
//       user.password
//     );
//     if (!passwordValidated) {
//       throw new Error();
//     }

//     await Session.expireAllTokensForUser(userId);
//     res.clearCookie('token');
//     await deleteUser(userId);
//     res.json({
//       title: 'Account Deleted',
//       detail: 'Account with credentials provided has been successfully deleted',
//     });
//   } catch (err) {
//     res.status(401).json({
//       errors: [
//         {
//           title: 'Invalid Credentials',
//           detail: 'Check email and password combination',
//           errorMessage: err.message,
//         },
//       ],
//     });
//   }
// });

// router.put('/logout', authenticate, csrfCheck, async (req, res, next) => {
//   try {
//     const { session } = req;
//     await session.expireToken(session.token);
//     res.clearCookie('token');

//     res.json({
//       title: 'Logout Successful',
//       detail: 'Successfully expired login session',
//     });
//   } catch (err) {
//     next(boomify(401, 'Logout Failed', err.message));
//   }
// });

module.exports = router;
