var express = require('express');
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var passport = require('passport');
var session = require('express-session')
var app = express();
var port = process.env.PORT || 5000;

//Navigation bar across all webpages
var nav = [
    {
        Link: '/Research',
        Text: 'Research'
    },
    {
        Link: '/Books',
        Text: 'Books'
    },
    {
        Link: '/Tasks',
        Text: 'Tasks'
    }
]

var researchRouter = require('./src/routes/researchRoutes')(nav);
var bookRouter = require('./src/routes/bookRoutes')(nav);
var taskRouter = require('./src/routes/taskRoutes')(nav);
var adminRouter = require('./src/routes/adminRoutes')(nav);
var authRouter = require('./src/routes/authRoutes')(nav);

app.use(express.static('public'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
app.use(cookieParser());
app.use(session({secret: 'library'}));

require('./src/config/passport')(app);

app.set('views', './src/views');

app.set('view engine', 'ejs');

app.use('/Research', researchRouter);
app.use('/Books', bookRouter);
app.use('/Tasks', taskRouter);
app.use('/Admin', adminRouter);
app.use('/Auth', authRouter);

// Home Page - Refactor later to home router and home controller
app.get('/', function (req, res) {
    res.render('index', {
        title: 'hello from render',
        nav: nav
    });
});

app.listen(port, function (err) {
    console.log('running server on port ' + port);
});
