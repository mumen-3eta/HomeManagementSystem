const router = require('express').Router();
const { addUsers, login, authUser } = require('../../controllers');
// Ex: {userName, email, password}
router.post('/register', addUsers);
router.post('/login', login);
router.get('/me', authUser);
// router.post('/register', async (req, res, next) => {
//   try {
//     const { userName, email, password } = req.body;

//     if (!isEmail(email)) {
//       throw new Error('Email must be a valid email address.');
//     }
//     if (typeof password !== 'string') {
//       throw new Error('Password must be a string.');
//     }

//     const userData = {
//       basicInfo: { userName, email, password },
//       profileInfo: { firstName: null, lastName: null, mobile: null },
//     };

//     // const user = new User(userData);
//     // const persistedUser = await user.save();
//     // const userId = persistedUser._id;

//     const { rows: userId } = await signUp(userData);

//     const session = await initSession(userId);

//     res
//       .cookie('token', session.token, {
//         httpOnly: true,
//         sameSite: true,
//         maxAge: 1209600000,
//         secure: process.env.NODE_ENV === 'production',
//       })
//       .status(201)
//       .json({
//         title: 'User Registration Successful',
//         detail: 'Successfully registered new user',
//         csrfToken: session.csrfToken,
//       });
//   } catch (err) {
//     next(boomify(400, 'Registration Error', err.message));
//   }
// });

// // eslint-disable-next-line consistent-return
// router.post('/login', async (req, res, next) => {
//   try {
//     console.log();
//     const { email, password } = req.body;
//     if (!isEmail(email)) {
//       return res.status(400).json({
//         errors: [
//           {
//             title: 'Bad Request',
//             detail: 'Email must be a valid email address',
//           },
//         ],
//       });
//     }
//     if (typeof password !== 'string') {
//       return res.status(400).json({
//         errors: [
//           {
//             title: 'Bad Request',
//             detail: 'Password must be a string',
//           },
//         ],
//       });
//     }
//     // const user = await User.findOne({ 'basicInfo.email': email });
//     const { rows: user } = await signIn(email);
//     if (!user) {
//       throw new Error();
//     }
//     // const userId = user._id;
//     const userId = user.id;

//     const passwordValidated = await bcrypt.compare(
//       password,
//       // user.basicInfo.password
//       user.password
//     );
//     if (!passwordValidated) {
//       throw new Error();
//     }

//     const session = await initSession(userId);

//     return res
//       .cookie('token', session.token, {
//         httpOnly: true,
//         sameSite: true,
//         maxAge: 1209600000,
//         secure: process.env.NODE_ENV === 'production',
//       })
//       .json({
//         title: 'Login Successful',
//         detail: 'Successfully validated user credentials',
//         csrfToken: session.csrfToken,
//       });
//   } catch (err) {
//     next(boomify(401, 'Invalid Credentials', err.message));
//   }
// });

// router.get('/me', authenticate, async (req, res, next) => {
//   try {
//     const { userId } = req.session;
//     // const user = await User.findById(
//     //   { _id: userId },
//     //   {
//     //     basicInfo: {
//     //       email: 1,
//     //       userName: 1,
//     //       isAdmin: 1,
//     //     },
//     //     profileInfo: {
//     //       image: 1,
//     //     },
//     //     _id: 0,
//     //   }
//     // );
//     // delete user.basicInfo.createdAt;
//     const { rows: user } = await getUserDataById(userId);
//     res.json({
//       title: 'Authentication successful',
//       detail: 'Successfully authenticated user',
//       user,
//     });
//   } catch (err) {
//     next(boomify(401, 'Not authorized to access this route', err.message));
//   }
// });

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
