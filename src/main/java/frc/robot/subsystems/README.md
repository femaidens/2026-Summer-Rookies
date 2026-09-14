What it's SUPPOSED to do at least

INTAKE SUBSYSTEM
│
├── PIVOT
│   │
│   ├── POSITION
│   │   └── getAbsolutePosition()
│   │
│   ├── LIMIT / PERMISSION
│   │   ├── canGoUp()
│   │   └── canGoDown()
│   │
│   ├── MANUAL CONTROL
│   │   ├── goUp()   → canGoUp()   if yes → helperGoUp() 
│   │   └── goDown() → canGoDown()  if yes → helperGoDown()
│   │
│   ├── AUTOMATIC CONTROL
│   │   ├── goToUpSetpoint()   → canGoUp()  if yes → PID → UP_SETPOINT
│   │   └── goToDownSetpoint() → canGoDown()  if yes→ PID → DOWN_SETPOINT
│   │
│   └── SAFETY
│       └── periodic()
│            └── safetyCheck()
│                 ├── > MAX → PID → UP_SETPOINT
│                 ├── < MIN → PID → DOWN_SETPOINT
│                 └── normal → do nothing
│
└── ROLLER
    │
    ├── intakeFuel()
    │     
    │
    ├── ejectFuel()
    │     └── roller OUT
    │
    └── stopRollers()
          └── roller STOP

** btw right now there are some things that need fixing, like periodic clashing with PID commands, putting in the actual setpoints, and figuring out how to stop the go up/down manual commands when the button is pressed/released and stop pivot BUT that will just be the fifth draft! :0