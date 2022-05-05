import React from "react";
import Button from "@mui/material/Button";

export default function MyButton(props) {
  let style = {
    backgroundColor: props.bgColor,
    color: props.fgColor,
    float: "right",
    filter: "drop-shadow(0px 5px 5px #dbdbdb)",
    marginRight: "10px",
    marginTop: props.marginTop || "1px",
    borderRadius: props.radius || 100,
    fontSize: props.size || "12px",
    fontFamily: "Montserrat",
    fontStyle: "bold",
    minWidth: "130px",
  };
  if (props.style !== undefined) {
    style = { ...style, ...props.style };
  }
  return (
    <Button
      style={style}
      variant="contained"
      color="primary"
      className={props.className}
      size="small"
      href={props.url}
      onClick={props.onClick}
      startIcon={props.startIcon}
    >
      {props.value}
    </Button>
  );
}
