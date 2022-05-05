import React from "react";
import Button from "@mui/material/Button";

function TextButton(props) {
  let style = {
    backgroundColor: props.bgColor,
    color: props.fgColor,
    borderRadius: 100,
    float: "right",
    filter: "drop-shadow(0px 5px 5px #dbdbdb)",
    marginRight: props.margin || "20px",
  };
  return (
    <Button
      style={style}
      className={props.className}
      size={props.size || "small"}
      href={props.url}
      onClick={props.onClick}
    >
      {props.value}
    </Button>
  );
}
export default TextButton;
