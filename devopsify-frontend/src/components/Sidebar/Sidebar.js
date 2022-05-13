import * as React from "react";
import { styled, useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import { Drawer, Collapse, ListItemButton } from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";
import List from "@mui/material/List";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import AddIcon from "@mui/icons-material/Add";
import ExpandLess from "@mui/icons-material/ExpandLess";
import ExpandMore from "@mui/icons-material/ExpandMore";
import { listProjects } from "../../api/projectService";
import { goto } from "../../utils/utils";
import config from "../../config.json"

const drawerWidth = config.DRAWER_WIDTH ;

const DrawerHeader = styled("div")(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  height: 70,
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
  justifyContent: "flex-end",
}));

export default function Sidebar({ handleCloseNavMenu, open }) {
  const [projects, setProjects] = React.useState([]);
  const theme = useTheme();

  const [projectsOpen, setProjectsOpen] = React.useState(true);

  const handleClick = () => {
    setProjectsOpen(!projectsOpen);
  };

  const handleDrawerClose = () => {
    handleCloseNavMenu();
  };

  React.useEffect(() => {
    listProjects(setProjects);
  }, []);

  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: drawerWidth,
            boxSizing: "border-box",
          },
        }}
        variant="persistent"
        anchor="left"
        open={open}
      >
        <DrawerHeader>
          <Typography
            variant="h6"
            noWrap
            component="div"
            sx={{ mx: "auto", display: "flex" }}
            fontWeight="550"
          >
            DevOpsify
          </Typography>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === "ltr" ? (
              <ChevronLeftIcon />
            ) : (
              <ChevronRightIcon />
            )}
          </IconButton>
        </DrawerHeader>
        <Divider />
        <List>
          <ListItemButton onClick={handleClick}>
            <ListItemText
              primary={<Typography type="h1">Projects</Typography>}
            />
            {projectsOpen ? <ExpandLess /> : <ExpandMore />}
          </ListItemButton>
          <Collapse in={projectsOpen} timeout="auto" unmountOnExit>
            <List>
              {projects.map((project) => (
                <ListItem button key={project.projectId} sx={{ pl: 4 }}>
                  <ListItemText
                    primary={
                      <Typography type="body2" style={{ fontSize: 17 }}>
                        {project.name}
                      </Typography>
                    }
                  />
                </ListItem>
              ))}
              <ListItem button sx={{ pl: 4 }}>
                <AddIcon fontSize="small" color="grey" sx={{ mr: 1 }} />
                <ListItemText
                  disableTypography
                  primary={
                    <Typography
                      type="body2"
                      style={{ color: "#787885", fontSize: 17 }}
                    >
                      create new project
                    </Typography>
                  }
                  onClick={() => {
                    console.log("create new project clicked");
                    goto("/project/create");
                  }}
                />
              </ListItem>
            </List>
          </Collapse>
        </List>
      </Drawer>
    </Box>
  );
}
