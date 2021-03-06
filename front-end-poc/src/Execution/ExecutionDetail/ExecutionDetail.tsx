import React, { useCallback } from "react";
import { useHistory } from "react-router-dom";
import { PageSection, Stack, StackItem, Title } from "@patternfly/react-core";
import { IExecution, IExecutionModelResponse } from "../../Audit/types";
import Outcomes from "../Outcomes/Outcomes";
import SkeletonCards from "../../Shared/skeletons/SkeletonCards/SkeletonCards";
import { RemoteData } from "../../Shared/types";
import { IOutcome } from "../../Outcome/types";
import "./ExecutionDetail.scss";

type ExecutionDetailProps = {
  execution: RemoteData<Error, IExecution>;
  outcome: RemoteData<Error, IOutcome[]>;
  model: IExecutionModelResponse;
};

const ExecutionDetail = (props: ExecutionDetailProps) => {
  const { execution, model, outcome } = props;
  const history = useHistory();
  console.log(model.name, execution);
  const goToExplanation = useCallback(
    (outcomeId: string) => {
      history.push({
        pathname: "outcomes-details",
        search: `?outcomeId=${outcomeId}`,
      });
    },
    [history]
  );

  return (
    <section className="execution-detail">
      <PageSection variant="default">
        <Stack hasGutter>
          <StackItem>
            <Title headingLevel="h3" size="2xl">
              Outcomes
            </Title>
          </StackItem>
          <StackItem>
            {outcome.status === "LOADING" && <SkeletonCards quantity={2} />}
            {outcome.status === "SUCCESS" && (
              <Outcomes outcomes={outcome.data} onExplanationClick={goToExplanation} listView />
            )}
          </StackItem>
        </Stack>
      </PageSection>
    </section>
  );
};

export default ExecutionDetail;
